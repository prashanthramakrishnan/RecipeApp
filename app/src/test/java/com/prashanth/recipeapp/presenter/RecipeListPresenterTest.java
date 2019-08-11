package com.prashanth.recipeapp.presenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import com.prashanth.recipeapp.contract.APIContract;
import com.prashanth.recipeapp.model.RecipeResponse;
import com.prashanth.recipeapp.network.RecipeAPI;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RecipeListPresenterTest {

    private static final String DUMMY = "dummy";

    @Mock
    private APIContract.RecipeListView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void getDataAndLoadViewTest() {
        RecipeListPresenter presenter = new RecipeListPresenter(provideRecipeAPI(true));
        presenter.fetchRecipe(DUMMY, DUMMY, view);
        Mockito.verify(view, times(1)).callStarted();
        Mockito.verify(view, times(1)).onResponseRecipeList(any(), any(), any());
        Mockito.verify(view, times(1)).callComplete();
    }

    @Test
    public void getDataAndLoadViewFailTest() {
        RecipeListPresenter presenter = new RecipeListPresenter(provideRecipeAPI(false));
        presenter.fetchRecipe(DUMMY, DUMMY, view);
        Mockito.verify(view, times(1)).callFailed(any(Throwable.class));
    }

    @Test
    public void unsubscribeTest() {
        RecipeListPresenter presenter = new RecipeListPresenter(provideRecipeAPI(false));
        presenter.unsubscribe();
        Mockito.verifyZeroInteractions(view);
    }

    @Test
    public void onDestroyTest() {
        RecipeListPresenter presenter = new RecipeListPresenter(provideRecipeAPI(false));
        presenter.onDestroy();
        Mockito.verifyZeroInteractions(view);
    }

    private RecipeAPI provideRecipeAPI(boolean success) {
        if (success) {
            return new MockRecipeResponseSuccess();
        }
        return new MockRecipeResponseFailure();
    }

    private class MockRecipeResponseSuccess implements RecipeAPI {

        @Override
        public Observable<RecipeResponse> getRecipes(String spaceID, String accessToken) {
            return Observable.just(new RecipeResponse());
        }
    }

    private class MockRecipeResponseFailure implements RecipeAPI {

        @Override
        public Observable<RecipeResponse> getRecipes(String spaceID, String accessToken) {
            return Observable.error(Throwable::new);
        }
    }

}
