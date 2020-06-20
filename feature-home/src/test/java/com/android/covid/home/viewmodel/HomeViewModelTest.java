package com.android.covid.home.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.covid.home.model.NovelCovidDetail;
import com.android.covid.home.model.repo.CovidSummaryRepo;
import com.android.covid.network.State;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private Observer<State> stateObserver;
    @Mock
    Observer<NovelCovidDetail> covidDetailObserver;
    @Mock
    private MutableLiveData<NovelCovidDetail> getNovelCovidDetail;
    @Mock
    private MutableLiveData<State> getIsLoading;
    @Mock
    CovidSummaryRepo covidSummaryRepo;
    @Mock
    LifecycleOwner lifecycleOwner;

    private HomeViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Lifecycle lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new HomeViewModel();
        viewModel.getIsLoading().observeForever(stateObserver);
        viewModel.getCovidSummary().observeForever(covidDetailObserver);
    }

    @After
    public void tearDown() {
        covidSummaryRepo = null;
        viewModel = null;
    }

    @Test
    public void testWhenGlobalSummartDataIsNull() {
        when(covidSummaryRepo.getCovidSummary()).thenReturn(null);
        assertNotNull(viewModel.getCovidSummary());
        assertTrue(viewModel.getCovidSummary().hasObservers());
    }

    @Test
    public void testWhenIsLoadingDataIsNull() {
        when(covidSummaryRepo.getIsLoading()).thenReturn(null);
        assertNotNull(viewModel.getIsLoading());
        assertTrue(viewModel.getIsLoading().hasObservers());
    }

    @Test
    public void testWhenGlobalSummaryHasData() {
        givenGlobalSummaryHasData();
        when(covidSummaryRepo.getCovidSummary()).thenReturn(getNovelCovidDetail);
        assertTrue(viewModel.getCovidSummary().hasObservers());
    }

    //////////////////////////////////////////////////////////////////////////
    //                                 GIVEN                                 //
    //////////////////////////////////////////////////////////////////////////

    private void givenGlobalSummaryHasData() {
        given(getNovelCovidDetail.getValue()).willReturn(new NovelCovidDetail());
    }

    //////////////////////////////////////////////////////////////////////////
    //                                 WHEN                                 //
    //////////////////////////////////////////////////////////////////////////


    private void whenGlobalSummaryIsEmpty() {
        when(getNovelCovidDetail.getValue()).thenReturn(null);
    }

    private void whenGetCovidSummaryCalled() {
        when(covidSummaryRepo.getCovidSummary()).thenReturn(getNovelCovidDetail);
    }

    private void whenGetIsLoadingCalled() {
        when(covidSummaryRepo.getIsLoading()).thenReturn(getIsLoading);
    }

    private void whenGetStateWithFailed() {
        given(getIsLoading.getValue())
                .willReturn(new State(State.Status.FAILED, "Error Simulation"));
    }

    private void whenGetStateWithLoading() {
        given(getIsLoading.getValue())
                .willReturn(State.LOADING);
    }

    private void whenGetStateWithSuccess() {
        when(getIsLoading.getValue())
                .thenReturn(State.SUCCESS);
    }

    //////////////////////////////////////////////////////////////////////////
    //                                 THEN                                 //
    //////////////////////////////////////////////////////////////////////////

    private void thenShouldHaveObservers() {
        then(viewModel.getCovidSummary()).should().hasObservers();
        then(viewModel.getIsLoading()).should().hasObservers();
    }

}