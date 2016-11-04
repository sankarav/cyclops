package com.aol.cyclops.hkt;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import com.aol.cyclops.hkt.alias.Higher;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Simulates Higher Kinded Types for CompletableFuture's
 * 
 * CompletableFutureType is a CompletableFuture and a Higher Kinded Type (CompletableFutureType.µ,T)
 * 
 * @author johnmcclean
 *
 * @param <T> Data type stored within the CompletableFuture
 */

public interface CompletableFutureType<T> extends Higher<CompletableFutureType.µ, T>, CompletionStage<T> {

    /**
     * Witness type
     * 
     * @author johnmcclean
     *
     */
    public static class µ {
    }

    /**
     * Convert a CompletableFuture to a simulated HigherKindedType that captures CompletableFuture nature
     * and CompletableFuture element data type separately. Recover via @see CompletableFutureType#narrow
     * 
     * If the supplied CompletableFuture implements CompletableFutureType it is returned already, otherwise it
     * is wrapped into a CompletableFuture implementation that does implement CompletableFutureType
     * 
     * @param CompletableFuture CompletableFuture to widen to a CompletableFutureType
     * @return CompletableFutureType encoding HKT info about CompletableFutures
     */
    public static <T> CompletableFutureType<T> widen(final CompletionStage<T> completableFuture) {
        if (completableFuture instanceof CompletionStage)
            return (CompletableFutureType<T>) completableFuture;
        return new Box<>(
                         completableFuture);
    }

    /**
     * Convert the HigherKindedType definition for a CompletableFuture into
     * 
     * @param CompletableFuture Type Constructor to convert back into narrowed type
     * @return CompletableFuture from Higher Kinded Type
     */
    public static <T> CompletableFuture<T> narrow(final Higher<CompletableFutureType.µ, T> completableFuture) {
        if (completableFuture instanceof CompletionStage) {
            final CompletionStage<T> ft = (CompletionStage<T>) completableFuture;
            return CompletableFuture.completedFuture(1)
                                    .thenCompose(f -> ft);
        }
        // this code should be unreachable due to HKT type checker
        final Box<T> type = (Box<T>) completableFuture;
        final CompletionStage<T> stage = type.narrow();
        return CompletableFuture.completedFuture(1)
                                .thenCompose(f -> stage);

    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static final class Box<T> implements CompletableFutureType<T> {

        private final CompletionStage<T> boxed;

        /**
         * @return wrapped CompletableFuture
         */
        public CompletionStage<T> narrow() {
            return boxed;
        }

        @Override
        public <U> CompletionStage<U> thenApply(final Function<? super T, ? extends U> fn) {
            return boxed.thenApply(fn);
        }

        @Override
        public <U> CompletionStage<U> thenApplyAsync(final Function<? super T, ? extends U> fn) {
            return boxed.thenApplyAsync(fn);
        }

        @Override
        public <U> CompletionStage<U> thenApplyAsync(final Function<? super T, ? extends U> fn,
                final Executor executor) {
            return boxed.thenApplyAsync(fn, executor);
        }

        @Override
        public CompletionStage<Void> thenAccept(final Consumer<? super T> action) {
            return boxed.thenAccept(action);
        }

        @Override
        public CompletionStage<Void> thenAcceptAsync(final Consumer<? super T> action) {
            return boxed.thenAcceptAsync(action);
        }

        @Override
        public CompletionStage<Void> thenAcceptAsync(final Consumer<? super T> action, final Executor executor) {
            return boxed.thenAcceptAsync(action, executor);
        }

        @Override
        public CompletionStage<Void> thenRun(final Runnable action) {
            return boxed.thenRun(action);
        }

        @Override
        public CompletionStage<Void> thenRunAsync(final Runnable action) {
            return boxed.thenRunAsync(action);
        }

        @Override
        public CompletionStage<Void> thenRunAsync(final Runnable action, final Executor executor) {
            return boxed.thenRunAsync(action, executor);
        }

        @Override
        public <U, V> CompletionStage<V> thenCombine(final CompletionStage<? extends U> other,
                final BiFunction<? super T, ? super U, ? extends V> fn) {
            return boxed.thenCombine(other, fn);
        }

        @Override
        public <U, V> CompletionStage<V> thenCombineAsync(final CompletionStage<? extends U> other,
                final BiFunction<? super T, ? super U, ? extends V> fn) {
            return boxed.thenCombineAsync(other, fn);
        }

        @Override
        public <U, V> CompletionStage<V> thenCombineAsync(final CompletionStage<? extends U> other,
                final BiFunction<? super T, ? super U, ? extends V> fn, final Executor executor) {
            return boxed.thenCombineAsync(other, fn, executor);
        }

        @Override
        public <U> CompletionStage<Void> thenAcceptBoth(final CompletionStage<? extends U> other,
                final BiConsumer<? super T, ? super U> action) {
            return boxed.thenAcceptBoth(other, action);
        }

        @Override
        public <U> CompletionStage<Void> thenAcceptBothAsync(final CompletionStage<? extends U> other,
                final BiConsumer<? super T, ? super U> action) {
            return boxed.thenAcceptBothAsync(other, action);
        }

        @Override
        public <U> CompletionStage<Void> thenAcceptBothAsync(final CompletionStage<? extends U> other,
                final BiConsumer<? super T, ? super U> action, final Executor executor) {
            return boxed.thenAcceptBothAsync(other, action, executor);
        }

        @Override
        public CompletionStage<Void> runAfterBoth(final CompletionStage<?> other, final Runnable action) {
            return boxed.runAfterBoth(other, action);
        }

        @Override
        public CompletionStage<Void> runAfterBothAsync(final CompletionStage<?> other, final Runnable action) {
            return boxed.runAfterBothAsync(other, action);
        }

        @Override
        public CompletionStage<Void> runAfterBothAsync(final CompletionStage<?> other, final Runnable action,
                final Executor executor) {
            return boxed.runAfterBothAsync(other, action, executor);
        }

        @Override
        public <U> CompletionStage<U> applyToEither(final CompletionStage<? extends T> other,
                final Function<? super T, U> fn) {
            return boxed.applyToEither(other, fn);
        }

        @Override
        public <U> CompletionStage<U> applyToEitherAsync(final CompletionStage<? extends T> other,
                final Function<? super T, U> fn) {
            return boxed.applyToEitherAsync(other, fn);
        }

        @Override
        public <U> CompletionStage<U> applyToEitherAsync(final CompletionStage<? extends T> other,
                final Function<? super T, U> fn, final Executor executor) {
            return boxed.applyToEitherAsync(other, fn, executor);
        }

        @Override
        public CompletionStage<Void> acceptEither(final CompletionStage<? extends T> other,
                final Consumer<? super T> action) {
            return boxed.acceptEither(other, action);
        }

        @Override
        public CompletionStage<Void> acceptEitherAsync(final CompletionStage<? extends T> other,
                final Consumer<? super T> action) {
            return boxed.acceptEitherAsync(other, action);
        }

        @Override
        public CompletionStage<Void> acceptEitherAsync(final CompletionStage<? extends T> other,
                final Consumer<? super T> action, final Executor executor) {
            return boxed.acceptEitherAsync(other, action, executor);
        }

        @Override
        public CompletionStage<Void> runAfterEither(final CompletionStage<?> other, final Runnable action) {
            return boxed.runAfterEither(other, action);
        }

        @Override
        public CompletionStage<Void> runAfterEitherAsync(final CompletionStage<?> other, final Runnable action) {
            return boxed.runAfterEitherAsync(other, action);
        }

        @Override
        public CompletionStage<Void> runAfterEitherAsync(final CompletionStage<?> other, final Runnable action,
                final Executor executor) {
            return boxed.runAfterEitherAsync(other, action, executor);
        }

        @Override
        public <U> CompletionStage<U> thenCompose(final Function<? super T, ? extends CompletionStage<U>> fn) {
            return boxed.thenCompose(fn);
        }

        @Override
        public <U> CompletionStage<U> thenComposeAsync(final Function<? super T, ? extends CompletionStage<U>> fn) {
            return boxed.thenComposeAsync(fn);
        }

        @Override
        public <U> CompletionStage<U> thenComposeAsync(final Function<? super T, ? extends CompletionStage<U>> fn,
                final Executor executor) {
            return boxed.thenComposeAsync(fn, executor);
        }

        @Override
        public CompletionStage<T> exceptionally(final Function<Throwable, ? extends T> fn) {
            return boxed.exceptionally(fn);
        }

        @Override
        public CompletionStage<T> whenComplete(final BiConsumer<? super T, ? super Throwable> action) {
            return boxed.whenComplete(action);
        }

        @Override
        public CompletionStage<T> whenCompleteAsync(final BiConsumer<? super T, ? super Throwable> action) {
            return boxed.whenCompleteAsync(action);
        }

        @Override
        public CompletionStage<T> whenCompleteAsync(final BiConsumer<? super T, ? super Throwable> action,
                final Executor executor) {
            return boxed.whenCompleteAsync(action, executor);
        }

        @Override
        public <U> CompletionStage<U> handle(final BiFunction<? super T, Throwable, ? extends U> fn) {
            return boxed.handle(fn);
        }

        @Override
        public <U> CompletionStage<U> handleAsync(final BiFunction<? super T, Throwable, ? extends U> fn) {
            return boxed.handleAsync(fn);
        }

        @Override
        public <U> CompletionStage<U> handleAsync(final BiFunction<? super T, Throwable, ? extends U> fn,
                final Executor executor) {
            return boxed.handleAsync(fn, executor);
        }

        @Override
        public CompletableFuture<T> toCompletableFuture() {
            return boxed.toCompletableFuture();
        }

    }
}