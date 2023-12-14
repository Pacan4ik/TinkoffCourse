package edu.hw10.task1;

public record MyRecord(@Min(0) @Max(3) short shortValue, @NotNull @Min(0) int[] arrayValue) {
}
