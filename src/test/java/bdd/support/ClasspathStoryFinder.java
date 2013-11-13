package bdd.support;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.io.FileUtils.listFiles;

public class ClasspathStoryFinder {
    private static final Logger LOG = LoggerFactory.getLogger(ClasspathStoryFinder.class);

    public static List<String> findFilenamesThatMatch(String aFilenameWithWildcards) {
        List<String> filenames = new ArrayList<String>();
        for (File file : findFilesThatMatch(aFilenameWithWildcards)) {
            filenames.add(file.toURI().toString());
        }
        return filenames;
    }

    private static Collection<File> findFilesThatMatch(String aFilenameWithWildcards) {
        WildcardFileFilter regexFileFilter = new WildcardFileFilter(aFilenameWithWildcards);
        File rootDirToSearchFrom = getRootDir();
        LOG.info("Searching for stories called [{}] in [{}]", aFilenameWithWildcards, rootDirToSearchFrom);
        return listFiles(rootDirToSearchFrom, regexFileFilter, DirectoryFileFilter.DIRECTORY);
    }

    private static File getRootDir() {
        return new File(ClasspathStoryFinder.class.getClassLoader().getResource("").getFile());
    }
}
