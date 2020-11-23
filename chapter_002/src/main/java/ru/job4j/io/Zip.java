package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for(File el : sources) {
                zip.putNextEntry(new ZipEntry(el.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(el))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );

        ArgZip arg = new ArgZip(args);
        List<File> sources = Files.walk(Paths.get(arg.directory()))
                .filter(Files::isRegularFile)
                .filter(p -> !p.toFile().getName().endsWith(arg.exclude()))
                .map(Path::toFile)
                .collect(Collectors.toList());

        new Zip().packFiles(sources, new File(arg.output()));
    }
}
