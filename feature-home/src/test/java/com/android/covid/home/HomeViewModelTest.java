package com.android.covid.home;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.repo.CovidSummaryRepo;
import com.android.covid.home.repo.web.CovidGlobalService;
import com.android.covid.home.ui.viewmodel.HomeViewModel;
import com.android.covid.network.State;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class HomeViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    CovidGlobalService apiEndPoint;
    @Mock
    CovidSummaryRepo apiClient;
    @Mock
    Observer<State> observer;
    @Mock
    Observer<NovelCovidDetail> observer2;
    @Mock
    LifecycleOwner lifecycleOwner;


    private HomeViewModel viewModel;
    Lifecycle lifecycle;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new HomeViewModel();
        viewModel.getIsLoading().observeForever(observer);
        viewModel.getCovidSummary().observeForever(observer2);

    }

    @Test
    public void testNull() {
        when(apiClient.getCovidSummary()).thenReturn(null);
        assertNotNull(viewModel.getCovidSummary());
        assertTrue(viewModel.getCovidSummary().hasObservers());
    }/*

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        when(apiClient.fetchNews()).thenReturn(Single.just(new NewsList()));
        viewModel.fetchNews();
        verify(observer).onChanged(NewsListViewState.LOADING_STATE);
        verify(observer).onChanged(NewsListViewState.SUCCESS_STATE);
    }

    @Test
    public void testApiFetchDataError() {
        when(apiClient.fetchNews()).thenReturn(Single.error(new Throwable("Api error")));
        viewModel.fetchNews();
        verify(observer).onChanged(NewsListViewState.LOADING_STATE);
        verify(observer).onChanged(NewsListViewState.ERROR_STATE);
    }*/

    @After
    public void tearDown() throws Exception {
        apiClient = null;
        viewModel = null;
    }
}