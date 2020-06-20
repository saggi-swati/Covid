package com.android.covid.home.model.web;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.android.covid.home.model.NovelCovidGlobalStats;
import com.covid.util.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import kotlin.jvm.JvmField;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CovidGlobalServiceTest {
    @Rule
    @JvmField
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private CovidGlobalService service;

    private MockWebServer mockWebServer;

    @Before
    public void createService() {
        mockWebServer = new MockWebServer();
        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidGlobalService.class);
    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testGetCovidGlobalStatsWithCorrectResponse() {

        try {
            mockWebServer.enqueue(new MockResponse()
                    .setBody(TestUtils
                            .readFileAsString(this, "response.json")));
            Call<NovelCovidGlobalStats> call = service.getCovidGlobalStats();
            Response<NovelCovidGlobalStats> response = call.execute();
            assert response.body() != null;
            assert !response.body().globalStats.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testGetCovidGlobalStatsWithErrorCode() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));
        Call<NovelCovidGlobalStats> call = service.getCovidGlobalStats();

        try {
            assertEquals(call.execute().code(), 404 );
        } catch (IOException e) {
            assert false;
        }
    }
}
