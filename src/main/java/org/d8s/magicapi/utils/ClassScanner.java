package org.d8s.magicapi.utils;


import org.d8s.script.functions.ObjectConvertExtension;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ClassScanner {

	public static List<String> scan() throws URISyntaxException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Set<String> classes = new HashSet<>();
		do {
			if (loader instanceof URLClassLoader) {
				classes.addAll(scan(((URLClassLoader) loader).getURLs()));
			}
		} while ((loader = loader.getParent()) != null);
		classes.addAll(addJavaLibrary());
		return new ArrayList<>(classes);
	}

	private static Set<String> scan(URL[] urls) throws URISyntaxException {
		Set<String> classes = new HashSet<>();
		if (urls != null) {
			for (URL url : urls) {
				String protocol = url.getProtocol();
				if ("file".equalsIgnoreCase(protocol)) {
					String path = url.getPath();
					if (path.toLowerCase().endsWith(".jar")) {
						classes.addAll(scanJarFile(url));
					} else {
						classes.addAll(scanDirectory(new File(url.toURI()), null));
					}
				} else if ("jar".equalsIgnoreCase(protocol)) {
					classes.addAll(scanJarFile(url));
				}
			}
		}
		return classes;
	}

	private static Set<String> addJavaLibrary() {
		int version = checkJavaVersion();
		if (version >= 9) {
			return addJava9PlusLibrary();
		}
		return addJava8Library();
	}

	private static int checkJavaVersion() {
		String version = System.getProperty("java.version");
		int index = version.indexOf(".");
		if (index > -1) {
			String first = version.substring(0, index);
			if (!"1".equals(first)) {
				return ObjectConvertExtension.asInt(first, -1);
			} else {
				int endIndex = version.indexOf(".", index + 1);
				return ObjectConvertExtension.asInt(version.substring(index + 1, endIndex), -1);
			}
		}
		return -1;
	}

	/**
	 * jdk 8
	 */
	private static Set<String> addJava8Library() {
		try {
			// 直接反射调用..
			Object URLClassPath = Class.forName("sun.misc.Launcher").getMethod("getBootstrapClassPath").invoke(null);
			return scan((URL[]) URLClassPath.getClass().getMethod("getURLs").invoke(URLClassPath));
		} catch (Exception ignored) {
		}
		return Collections.emptySet();
	}

	/**
	 * jdk 9+
	 */
	private static Set<String> addJava9PlusLibrary() {
		Set<String> classes = new HashSet<>();
		try {
			//		ModuleLayer.boot().configuration().modules().stream().map(ResolvedModule::reference).forEach(ref -> {
			//			try (ModuleReader reader = ref.open()) {
			//				reader.list().forEach(System.out::println);
			//			} catch (IOException e) {
			//				e.printStackTrace();
			//			}
			//		});
			Class<?> ModuleLayer = Class.forName("java.lang.ModuleLayer");
			Object boot = ModuleLayer.getMethod("boot").invoke(null);
			Object configuration = ModuleLayer.getMethod("configuration").invoke(boot);
			Class<?> Configuration = Class.forName("java.lang.module.Configuration");
			//Set<ResolvedModule>
			Set<?> modules = (Set<?>) Configuration.getMethod("modules").invoke(configuration);
			Method reference = Class.forName("java.lang.module.ResolvedModule").getMethod("reference");
			Class<?> ModuleReader = Class.forName("java.lang.module.ModuleReader");
			Method open = Class.forName("java.lang.module.ModuleReference").getMethod("open");
			Method list = ModuleReader.getMethod("list");
			modules.forEach(module -> {
			});
			for (Object module : modules) {
				Object ref = reference.invoke(module);
				try (Closeable reader = (Closeable) open.invoke(ref)) {
					@SuppressWarnings("unchecked")
					Stream<String> stream = (Stream<String>) list.invoke(reader);
					stream.filter(ClassScanner::isClass).forEach(className -> classes.add(className.substring(0, className.length() - 6).replace("/", ".")));
				} catch (IOException ignored) {
				}
			}
		} catch (Exception ignored) {
		}
		return classes;
	}

	private static List<String> scanDirectory(File dir, String packageName) {
		File[] files = dir.listFiles();
		List<String> classes = new ArrayList<>();
		if (files != null) {
			for (File file : files) {
				String name = file.getName();
				if (file.isDirectory()) {
					classes.addAll(scanDirectory(file, packageName == null ? name : packageName + "." + name));
				} else if (name.endsWith(".class") && !name.contains("$")) {
					classes.add(filterFullName(packageName + "." + name.substring(0, name.length() - 6)));
				}
			}
		}
		return classes;
	}

	private static String filterFullName(String fullName) {
		if (fullName.startsWith("BOOT-INF.classes.")) {
			fullName = fullName.substring(17);
		}
		return fullName;
	}

	private static List<String> scanJarFile(URL url) {
		List<String> classes = new ArrayList<>();
		try (ZipInputStream zis = new ZipInputStream(url.openStream())) {
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (!entry.getName().contains("META-INF")) {
					String className = entry.getName();
					if (isClass(className)) {
						classes.add(filterFullName(className.substring(0, className.length() - 6).replace("/", ".")));
					}
				}
			}
		} catch (IOException ignored) {

		}
		return classes;
	}

	private static boolean isClass(String className) {
		return className.endsWith(".class") && !className.contains("$");
	}
}
