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

    public static final String PATH_2000x600 = "src/test/to_test_pics/2000x600/";
    public static final String PATH_200x200 = "src/test/to_test_pics/200x200/";

    @State(Scope.Thread)
    public static class EncodeExecutionPlan2000x600 {

        @Param({"2000x600_BW_MIX.png",
                "2000x600_COLOR_MIX.png",
        })
        public String source;

        @Param({"2000x600_BW_MOUNTAINS.png",
                "2000x600_COLOR_MOUNTAINS.png"
        })
        public String secret;

        public BufferedImage sourceImage;
        public BufferedImage secretImage;

        private final Stegano stegano = new SteganoLSB();

        @Setup
        public void setup() throws IOException{
            sourceImage = ImageIO.read(new File(SteganoJMHBenchmark.PATH_2000x600 + source));
            secretImage = ImageIO.read(new File(SteganoJMHBenchmark.PATH_2000x600 + secret));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(3)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void encode2000x600THRPT(EncodeExecutionPlan2000x600 executionPlan) {
        executionPlan.stegano.encode(executionPlan.sourceImage, executionPlan.secretImage);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void encode2000x600AVG(EncodeExecutionPlan2000x600 executionPlan) {
        executionPlan.stegano.encode(executionPlan.sourceImage, executionPlan.secretImage);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void encode2000x600AVGSPLT(EncodeExecutionPlan2000x600 executionPlan) {
        executionPlan.stegano.encode(executionPlan.sourceImage, executionPlan.secretImage);
    }


    @State(Scope.Thread)
    public static class DecodeExecutionPlan200x200 {

        @Param({"200x200_BW_COWS.png",
                "200x200_COLOR_HORSE.png"
        })
        public String secret;

        public BufferedImage secretImage;

        private final Stegano stegano = new SteganoLSB();

        @Setup
        public void setup() throws IOException {
            secretImage = ImageIO.read(new File(SteganoJMHBenchmark.PATH_200x200 + secret));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(3)
    @Measurement(time = 1, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void decode200x200THRPT(DecodeExecutionPlan200x200 executionPlan) {
        executionPlan.stegano.decode(executionPlan.secretImage);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(time = 1, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void decode200x200AVG(DecodeExecutionPlan200x200 executionPlan) {
        executionPlan.stegano.decode(executionPlan.secretImage);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @Measurement(time = 1, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void decode200x200AVGSPLT(DecodeExecutionPlan200x200 executionPlan) {
        executionPlan.stegano.decode(executionPlan.secretImage);
    }


    @State(Scope.Thread)
    public static class DecodeExecutionPlan2000x600 {

        @Param({"2000x600_BW_MIX.png",
                "2000x600_COLOR_MIX.png",
        })
        public String secret;

        public BufferedImage secretImage;

        private final Stegano stegano = new SteganoLSB();

        @Setup
        public void setup() throws IOException {
            secretImage = ImageIO.read(new File(SteganoJMHBenchmark.PATH_2000x600 + secret));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(3)
    @Measurement(time = 1, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void decode2000x600THRPT(DecodeExecutionPlan2000x600 executionPlan) {
        executionPlan.stegano.decode(executionPlan.secretImage);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(time = 1, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void decode2000x600AVG(DecodeExecutionPlan2000x600 executionPlan) {
        executionPlan.stegano.decode(executionPlan.secretImage);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @Measurement(time = 1, iterations = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS, time = 1)
    public void decode2000x600AVGSPLT(DecodeExecutionPlan2000x600 executionPlan) {
        executionPlan.stegano.decode(executionPlan.secretImage);
    }
}
