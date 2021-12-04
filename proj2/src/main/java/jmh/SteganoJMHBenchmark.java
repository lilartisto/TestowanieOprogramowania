package jmh;

import org.openjdk.jmh.annotations.*;
import steg.Stegano;
import steg.SteganoLSB;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SteganoJMHBenchmark {

//    @Benchmark
//    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
//    public void testMethod(){}

    @State(Scope.Benchmark)
    public static class ExecutionPlan2000x600 {

        @Param({ "2000x600_BW_MIX.png",
                "2000x600_BW_MOUNTAINS.png",
                "2000x600_COLOR_MIX.png",
                "2000x600_COLOR_MOUNTAINS.png",
        })
        public String source;


        @Param({ "2000x600_BW_MIX.png",
                "2000x600_BW_MOUNTAINS.png",
                "2000x600_COLOR_MIX.png",
                "2000x600_COLOR_MOUNTAINS.png"
        })
        public String secret;

        private final Stegano stegano = new SteganoLSB();

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void encode2000x600(ExecutionPlan2000x600 executionPlan) throws IOException {
        String path = "proj2/src/test/to_test_pics/2000x600/";
        BufferedImage source = ImageIO.read(new File(path + executionPlan.source));
        BufferedImage secret = ImageIO.read(new File(path + executionPlan.secret));

        executionPlan.stegano.encode(source, secret);
    }
}
