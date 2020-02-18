package com.iat.utils.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Matches if map size satisfies a nested matcher.
 */
public class IsMapWithSize<K, V> extends FeatureMatcher<Map<? extends K, ? extends V>, Integer> {
    public IsMapWithSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "a map with size", "map size");
    }

    @Override
    protected Integer featureValueOf(Map<? extends K, ? extends V> actual) {
        return actual.size();
    }

    /**
     * Creates a matcher for {@link Map}s that matches when the <code>size()</code> method returns
     * a value that satisfies the specified matcher.
     * <p/>
     * For example:
     * <pre>assertThat(Map, mapWithSize(equalTo(2)))</pre>
     *
     * @param sizeMatcher a matcher for the size of an examined {@link Map}
     */
    @Factory
    public static <K, V> Matcher<Map<? extends K, ? extends V>> mapWithSize(Matcher<? super Integer> sizeMatcher) {
        return new IsMapWithSize<>(sizeMatcher);
    }

    /**
     * Creates a matcher for {@link Map}s that matches when the <code>size()</code> method returns
     * a value equal to the specified <code>size</code>.
     * <p/>
     * For example:
     * <pre>assertThat(Map, mapWithSize(2))</pre>
     *
     * @param size the expected size of an examined {@link Map}
     */
    @Factory
    public static <K, V> Matcher<Map<? extends K, ? extends V>> mapWithSize(int size) {
        Matcher<? super Integer> matcher = equalTo(size);
        return IsMapWithSize.mapWithSize(matcher);
    }

}
