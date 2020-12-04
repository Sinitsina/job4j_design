package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path el : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(el)));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(String.valueOf(el)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> searchForException(List<Path> sources, String s) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(s));
        for (Path file : sources) {
            Files.walkFileTree(file, searcher);
        }
        return searcher.getPaths();

        //2. Для поиска используйте класс из прошлого задания. Поиск вынесите в отдельный метод

        //return null;
    }

    //public static void search(final String pattern, final File folder, List<String> result) {
    //        for (final File f : folder.listFiles()) {
    //
    //            if (f.isDirectory()) {
    //                search(pattern, f, result);
    //            }
    //
    //            if (f.isFile()) {
    //                if (f.getName().matches(pattern)) {
    //                    result.add(f.getAbsolutePath());
    //                }
    //            }
    //
    //        }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );

        ArgZip arg = new ArgZip(args);

        /*List<Path> sources = Files.walk(Paths.get(arg.directory()))
                .filter(Files::isRegularFile)
                //.filter(p -> !p.toFile().getName().endsWith(arg.exclude()))
                .map(Path::toFile)
                .collect(Collectors.toList());*/

        List<Path> sources = Files.list(Paths.get(arg.directory()))
                .filter(Files::isRegularFile)
                //.map(path -> path.resolve("..."))
                .collect(Collectors.toList());
        //searchForException(sources, arg.exclude());

        //File fileRoot = new File(arg.directory());

        //List<File> s = Arrays.asList(fileRoot.listFiles());

        new Zip().packFiles(searchForException(sources, arg.exclude()), new File(arg.output()));
    }
}
