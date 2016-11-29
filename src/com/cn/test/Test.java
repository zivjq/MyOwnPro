package com.cn.test;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class Test {

	public static void main(String[] args) {
		File desktopDir = FileSystemView.getFileSystemView()
				.getHomeDirectory();
				String desktopPath = desktopDir.getAbsolutePath();
				System.out.println(desktopPath);
	}
}
