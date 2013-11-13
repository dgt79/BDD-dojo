package bdd.dojo.simple;

import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static bdd.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;

/**
 * This class should 'only' mediate between the test and the core test code.  There should be
 * nothing of substance in here.
 */
public class SimpleFixture {

    private SimpleDriver driver;

    @Test
    public void test() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("simple.story")
                .run();
    }

    @BeforeStories
    public void runBefore() {
        driver = new SimpleDriver();
    }

}
