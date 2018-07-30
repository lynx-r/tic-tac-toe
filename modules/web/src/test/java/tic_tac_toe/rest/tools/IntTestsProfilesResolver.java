package tic_tac_toe.rest.tools;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.assertj.core.util.Lists;
import org.springframework.test.context.ActiveProfilesResolver;
import org.springframework.test.context.support.DefaultActiveProfilesResolver;

import static java.util.stream.Collectors.joining;

public class IntTestsProfilesResolver implements ActiveProfilesResolver {

    private static final String[] PROFILE_VAR_NAMES = {"spring.profiles.active", "SPRING_PROFILES_ACTIVE"};
    private final DefaultActiveProfilesResolver defaultResolver = new DefaultActiveProfilesResolver();

    @Override
    public String[] resolve(@NotNull Class<?> aClass) {
        Set<String> classProfiles = Sets.newHashSet(defaultResolver.resolve(aClass));
        classProfiles.addAll(getSpringProfilesActive());
        return classProfiles.toArray(new String[classProfiles.size()]);
    }

    private List<String> getSpringProfilesActive() {
        String profilesString = Arrays.stream(PROFILE_VAR_NAMES).
                flatMap(var -> this.values(var).stream()).
                filter(Objects::nonNull).
                collect(joining(",")).
                replaceAll("/s", "");
        return Splitter.on(",").splitToList(profilesString);
    }

    private List<String> values(String var) {
        return Lists.newArrayList(System.getenv(var), System.getProperty(var));
    }
}