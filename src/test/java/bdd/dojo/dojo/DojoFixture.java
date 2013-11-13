package bdd.dojo.dojo;

import org.junit.Test;

import static bdd.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;

public class DojoFixture {

    @Test
    public void test() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("dojo.story")
                .run();
    }
}
