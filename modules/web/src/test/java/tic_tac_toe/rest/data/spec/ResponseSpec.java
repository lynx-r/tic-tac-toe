package tic_tac_toe.rest.data.spec;

import com.google.common.base.Joiner;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import javax.annotation.Nullable;

public abstract class ResponseSpec {

    private static final Joiner PATH_JOINER = Joiner.on('.').skipNulls();

    protected abstract void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root);

    public ResponseSpecification withRoot(String root) {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        specBuilder.rootPath(root);
        applySpec(specBuilder, root);
        return specBuilder.build();
    }

    ResponseSpecification withRoot(String root, String... rootParts) {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        String fullRoot = PATH_JOINER.join(root, PATH_JOINER.join(rootParts));
        specBuilder.rootPath(fullRoot);
        applySpec(specBuilder, fullRoot);
        return specBuilder.build();
    }

    public ResponseSpecification withoutRoot() {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        applySpec(specBuilder, null);
        return specBuilder.build();
    }
}
