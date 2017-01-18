package com.aiyouwai.aphotoalbum.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.aiyouwai.aphotoalbum.R;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * crop（boolean） 是否剪切
 * ratio（int） 默认剪切框大小（0正方形，1是5比2）
 * @author zhangliucheng
 *
 */
public class ImagePickerFragment extends AppCompatDialogFragment {
	
	public static final int REQUEST_CAMERA = 1000;			// 照相机
	public static final int REQUEST_ALBUM = 1001;			// 相册
	public static final byte PHOTO_RESULT = 3;				// 结果
	
	private static final String IMAGE_UNSPECIFIED = "image/*";
	private static File file;
	private boolean crop;
	private int ratio;

	private TextView mCamera;
	private TextView mAlbum;
	private TextView mCancel;
	
	private ImagePickerListener listener;
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try {
			listener = (ImagePickerListener) context;
		} catch (Exception e) {
			throw new RuntimeException(context.getClass().getSimpleName() + " must implement ImagePickerListener");
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(AppCompatDialogFragment.STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
	}

	@Override
	public void onStart() {
		super.onStart();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
		getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (null != getArguments()) {
			crop = getArguments().getBoolean("crop");
			ratio = getArguments().getInt("ratio");
		}
		init();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_image_picker, container, false);
	}
	
	public void setListener(ImagePickerListener listener) {
		this.listener = listener;
	}

	public void showWithAnim(FragmentActivity activity) {
		file = getImagePickerPath(activity);

		FragmentManager manager = activity.getSupportFragmentManager();
		show(manager, null);
		manager.executePendingTransactions();
		Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_pop_enter);
		getView().findViewById(R.id.layout).startAnimation(animation);
	}

	public void dismissWithAnim() {
		Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_pop_exit);
		getView().findViewById(R.id.layout).startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				dismiss();
			}
		});
	}

	public void init() {
		mCamera = (TextView) getView().findViewById(R.id.camera);
		mAlbum = (TextView) getView().findViewById(R.id.album);
		mCancel = (TextView) getView().findViewById(R.id.cancel);

		mCamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toCamera();
			}
		});
		
		mAlbum.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toPhotoAlbum();
			}
		});
		
		getView().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onBitmap(null);
				dismissWithAnim();
			}
		});
		
		mCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onBitmap(null);
				dismissWithAnim();
			}
		});
	}
	
	// 跳转到照相机
	private void toCamera() {
		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		try{
			startActivityForResult(intent, REQUEST_CAMERA);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getActivity(), "无法使用相机拍张功能", Toast.LENGTH_SHORT).show();
		}	
	}
	
	// 跳转到相册
	private void toPhotoAlbum() {
		final Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("return-data", false);
		startActivityForResult(intent, REQUEST_ALBUM);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			listener.onBitmap(null);
			removeFragment();
			return;
		}
		if (requestCode == ImagePickerFragment.REQUEST_CAMERA) {	// 拍照图片回来
			imagePickFinish(file, Uri.fromFile(file));										
		} else if (requestCode == ImagePickerFragment.REQUEST_ALBUM) {	// 选取图片回来
			String pathChoosed = null;	
			if(data==null){
				Toast.makeText(getActivity(), "选取图片出现问题", Toast.LENGTH_SHORT).show();
				return;
			}
			if (data.getData() != null) {
				pathChoosed = getAlbumChoosedImgPath(data.getData());
			} else if (data.getAction() != null) {						// Note 1 等手机返回的是Action File
				pathChoosed = Uri.parse(data.getAction()).getPath();
			}
			if (null == pathChoosed || !new File(pathChoosed).exists()) {
				Toast.makeText(getActivity(), "选取图片出现问题", Toast.LENGTH_SHORT).show();
				return;
			}
			final boolean success = copyFile(new File(pathChoosed), file);
			if (success) {
				imagePickFinish(file, data.getData());
			}

		// 裁剪图片回来
		} else if (requestCode == PHOTO_RESULT) {// 处理结果
			if (file == null || file.length() == 0) {
				Toast.makeText(getActivity(), "出现错误！", Toast.LENGTH_SHORT).show();
				removeFragment();
				return;
			}
			listener.onBitmap(file.getAbsolutePath());
			removeFragment();
		}
	}
	
	private Bitmap decodeUriAsBitmap(Uri uri){
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();  
			bitmapOptions.inSampleSize = 4; 
			bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri), null, bitmapOptions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}
	
	private void imagePickFinish(File f, Uri uri) {
		if (crop) {
			startPhotoZoom(uri);
		} else {
			if (null != f) {
				listener.onBitmap(f.getAbsolutePath());
			} else {
				Toast.makeText(getActivity(), "内存不足，拍照失败...", Toast.LENGTH_SHORT).show();
			}
			removeFragment();
		}
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", true);// 滑动选中区域
		if (ratio == 0) {
			intent.putExtra("aspectX", 1);		// 宽高比例
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 100);	 // 裁剪图片宽高
			intent.putExtra("outputY", 100);
		} else {
			intent.putExtra("aspectX", 5);
			intent.putExtra("aspectY", 2);
			intent.putExtra("outputX", 500);	// 裁剪图片宽高
			intent.putExtra("outputY", 200);
		}
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("scale", true);
		intent.putExtra("scaleUpIfNeeded", true);// 黑边
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_RESULT);
	}

	// 获取从相册选取图片后的路径
	private String getAlbumChoosedImgPath(Uri selectedImageUri) {
		String result = null;
		if ("content".equals(selectedImageUri.getScheme())) {
			final Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, new String[] {"_data" }, null, null, null);
			if (null != cursor && cursor.moveToFirst()) {
				result = cursor.getString(0);
				cursor.close();
			}
		} else if ("file".equals(selectedImageUri.getScheme())){
			result = selectedImageUri.getPath();
		}
		return result;
	}
	
	private void removeFragment() {
		if (isAdded()) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					dismissWithAnim();
				}
			}, 100);
		}
	}

	private File getImagePickerPath(Context context) {
		File file = null;
		try {
			File rootFile;
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				rootFile = context.getExternalCacheDir();
			} else {
				rootFile = context.getCacheDir();
			}
			file = new File(rootFile, System.currentTimeMillis() + ".jpg");
			if (!file.exists()) {
				file.createNewFile();
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	// 拷贝文件
	public boolean copyFile(File src, File dest) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);
			final byte[] buf = new byte[1024];
			int count = 0;
			while (-1 != (count = is.read(buf))) {
				os.write(buf, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeStream(is);
			closeStream(os);
		}
		return true;
	}

	private void closeStream(Closeable stream) {
		if (null != stream) {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public interface ImagePickerListener {
		void onBitmap(String path);
	}
}
