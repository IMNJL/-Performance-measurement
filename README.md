# JMH Benchmark Results

## Environment
- **JMH version:** 1.37
- **VM version:** JDK 22.0.2, OpenJDK 64-Bit Server VM, 22.0.2+9-70
- **VM invoker:** `/Library/Java/JavaVirtualMachines/jdk-22.0.2.jdk/Contents/Home/bin/java`
- **VM options:** `-XX:+ShowCodeDetailsInExceptionMessages`
- **Blackhole mode:** compiler (auto-detected, use `-Djmh.blackhole.autoDetect=false` to disable)

## Benchmark Configuration
- **Warmup:** 5 iterations, 5 seconds each
- **Measurement:** 10 iterations, 5 seconds each
- **Timeout:** 10 minutes per iteration
- **Threads:** 1 thread, will synchronize iterations
- **Benchmark mode:** Average time, time/op

## Benchmarks

### `backend.academy.Main.directAccess`

#### Run Progress: 0.00% complete, ETA 00:10:00

##### Warmup Fork: 1 of 1
1. 0.869 ns/op
2. 0.861 ns/op
3. 0.860 ns/op
4. 0.859 ns/op
5. 0.860 ns/op

##### Measurement Iterations
1. 0.859 ns/op
2. 0.859 ns/op
3. 0.879 ns/op
4. 0.885 ns/op
5. 0.917 ns/op
6. 0.859 ns/op
7. 0.860 ns/op
8. 0.859 ns/op
9. 0.859 ns/op
10. 0.859 ns/op

#### Run Progress: 12.50% complete, ETA 00:10:05

##### Fork: 1 of 1

##### Warmup Iterations
1. 0.860 ns/op
2. 0.860 ns/op
3. 0.863 ns/op
4. 0.897 ns/op
5. 0.873 ns/op

##### Measurement Iterations
1. 0.869 ns/op
2. 0.874 ns/op
3. 0.860 ns/op
4. 0.859 ns/op
5. 0.860 ns/op
6. 0.860 ns/op
7. 0.860 ns/op
8. 0.859 ns/op
9. 0.859 ns/op
10. 0.860 ns/op

#### Result
- **Average:** 0.862 ±(99.9%) 0.008 ns/op
- **(min, avg, max):** (0.859, 0.862, 0.874)
- **Standard Deviation:** 0.005
- **Confidence Interval (99.9%):** [0.854, 0.870] (assumes normal distribution)

### `backend.academy.Main.lambdaMetafactory`

#### Run Progress: 25.00% complete, ETA 00:08:37

##### Warmup Fork: 1 of 1
1. 6.439 ns/op
2. 6.399 ns/op
3. 6.390 ns/op
4. 6.865 ns/op
5. 6.969 ns/op

##### Measurement Iterations
1. 6.800 ns/op
2. 7.355 ns/op
3. 6.466 ns/op
4. 7.549 ns/op
5. 7.218 ns/op
6. 6.481 ns/op
7. 6.448 ns/op
8. 6.487 ns/op
9. 6.457 ns/op
10. 6.473 ns/op

#### Run Progress: 37.50% complete, ETA 00:07:11

##### Fork: 1 of 1

##### Warmup Iterations
1. 7.119 ns/op
2. 6.541 ns/op
3. 6.420 ns/op
4. 8.299 ns/op
5. 7.651 ns/op

##### Measurement Iterations
1. 6.491 ns/op
2. 6.450 ns/op
3. 6.434 ns/op
4. 7.843 ns/op
5. 6.674 ns/op
6. 6.400 ns/op
7. 6.386 ns/op
8. 7.117 ns/op
9. 6.432 ns/op
10. 7.549 ns/op

#### Result
- **Average:** 6.778 ±(99.9%) 0.809 ns/op
- **(min, avg, max):** (6.386, 6.778, 7.843)
- **Standard Deviation:** 0.535
- **Confidence Interval (99.9%):** [5.968, 7.587] (assumes normal distribution)

### `backend.academy.Main.methodHandle`

#### Run Progress: 50.00% complete, ETA 00:05:46

##### Warmup Fork: 1 of 1
1. 7.744 ns/op
2. 6.760 ns/op
3. 6.493 ns/op
4. 6.616 ns/op
5. 6.696 ns/op

##### Measurement Iterations
1. 7.346 ns/op
2. 7.940 ns/op
3. 6.720 ns/op
4. 6.972 ns/op
5. 6.557 ns/op
6. 6.565 ns/op
7. 6.564 ns/op
8. 7.648 ns/op
9. 6.545 ns/op
10. 6.740 ns/op

#### Run Progress: 62.50% complete, ETA 00:04:19

##### Fork: 1 of 1

##### Warmup Iterations
1. 7.816 ns/op
2. 6.876 ns/op
3. 7.000 ns/op
4. 6.621 ns/op
5. 6.539 ns/op

##### Measurement Iterations
1. 6.531 ns/op
2. 7.260 ns/op
3. 7.343 ns/op
4. 7.294 ns/op
5. 7.301 ns/op
6. 7.820 ns/op
7. 7.424 ns/op
8. 7.740 ns/op
9. 7.356 ns/op
10. 7.470 ns/op

#### Result
- **Average:** 7.354 ±(99.9%) 0.523 ns/op
- **(min, avg, max):** (6.531, 7.354, 7.820)
- **Standard Deviation:** 0.346
- **Confidence Interval (99.9%):** [6.831, 7.877] (assumes normal distribution)

### `backend.academy.Main.reflection`

#### Run Progress: 75.00% complete, ETA 00:02:52

##### Warmup Fork: 1 of 1
1. 13.716 ns/op
2. 14.103 ns/op
3. 13.302 ns/op
4. 13.800 ns/op
5. 13.399 ns/op

##### Measurement Iterations
1. 13.274 ns/op
2. 13.303 ns/op
3. 13.354 ns/op
4. 13.224 ns/op
5. 13.149 ns/op
6. 13.212 ns/op
7. 13.224 ns/op
8. 13.222 ns/op
9. 13.202 ns/op
10. 13.213 ns/op

#### Run Progress: 87.50% complete, ETA 00:01:26

##### Fork: 1 of 1

##### Warmup Iterations
1. 17.654 ns/op
2. 18.198 ns/op
3. 17.235 ns/op
4. 17.244 ns/op
5. 17.261 ns/op

##### Measurement Iterations
1. 17.251 ns/op
2. 17.185 ns/op
3. 17.188 ns/op
4. 17.163 ns/op
5. 17.183 ns/op
6. 17.184 ns/op
7. 17.186 ns/op
8. 17.186 ns/op
9. 17.193 ns/op
10. 17.208 ns/op

#### Result
- **Average:** 17.193 ±(99.9%) 0.035 ns/op
- **(min, avg, max):** (17.163, 17.193, 17.251)
- **Standard Deviation:** 0.023
- **Confidence Interval (99.9%):** [17.157, 17.228] (assumes normal distribution)

## Summary

| Benchmark               | Mode | Cnt | Score   | Error  | Units |
|-------------------------|------|-----|---------|--------|-------|
| Main.directAccess       | avgt | 10  | 0.862   | ± 0.008| ns/op |
| Main.lambdaMetafactory  | avgt | 10  | 6.778   | ± 0.809| ns/op |
| Main.methodHandle       | avgt | 10  | 7.354   | ± 0.523| ns/op |
| Main.reflection         | avgt | 10  | 17.193  | ± 0.035| ns/op |

## Notes
- The numbers are just data. To gain reusable insights, you need to follow up on why the numbers are the way they are. Use profilers (see `-prof`, `-lprof`), design factorial experiments, perform baseline and negative tests that provide experimental control, make sure the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
- Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise extra caution when trusting the results, look into the generated code to check the benchmark still works, and factor in a small probability of new VM bugs. Additionally, while comparisons between different JVMs are already problematic, the performance difference caused by different Blackhole modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.

## Run Complete
- **Total time:** 00:11:30






Запуск линтеров:

```shell
mvn checkstyle:check modernizer:modernizer spotbugs:check pmd:check pmd:cpd-check
```

Вывод дерева зависимостей проекта (полезно при отладке транзитивных
зависимостей):

```shell
mvn dependency:tree
```

Вывод вспомогательной информации о любом плагине (вместо `compiler` можно
подставить интересующий вас плагин):

```shell
mvn help:describe -Dplugin=compiler
```

## Дополнительные материалы

- Документация по maven: https://maven.apache.org/guides/index.html
- Поиск зависимостей и их версий: https://central.sonatype.com/search
- Документация по процессу автоматизированной сборки в среде github:
  https://docs.github.com/en/actions
- Документация по git: https://git-scm.com/doc
- Javadoc для Java 22:
  https://docs.oracle.com/en/java/javase/22/docs/api/index.html

[course-url]: https://edu.tinkoff.ru/all-activities/courses/870efa9d-7067-4713-97ae-7db256b73eab
