package bdd.dojo;

import org.junit.Test;

import static bdd.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;

public class SimpleTest {

    @Test
    public void test() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("simple.story")
                .run();
    }
}
