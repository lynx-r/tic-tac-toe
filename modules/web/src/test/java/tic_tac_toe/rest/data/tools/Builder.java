package tic_tac_toe.rest.data.tools;

public abstract class Builder {

    private final DataProvider dataProvider;

    protected Builder(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public final DataProvider and() {
        onBuild();
        return dataProvider;
    }

    public final void build() {
        onBuild();
        dataProvider.build();
    }

    private void onBuild() {
    }
}
