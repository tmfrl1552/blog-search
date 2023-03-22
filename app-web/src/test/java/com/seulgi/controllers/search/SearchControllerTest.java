package com.seulgi.controllers.search;

import com.seulgi.MvcTest;
import com.seulgi.enums.ResponseCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SearchControllerTest extends MvcTest {

    @Test
    @DisplayName("블로그 검색 컨트롤러 성공 테스트")
    void searchBlogControllerTest() throws Exception {
        // given

        // when
        ResultActions response = mockMvc.perform(get("/v1.0/search/blog")
                        .param("query", "슬기")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "5"));

        // then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.code", is(ResponseCode.RESPONSE_OK.getCode())));
        response.andExpect(jsonPath("$.message", is(ResponseCode.RESPONSE_OK.getMessage())));
        response.andExpect(jsonPath("$.value.length()", not(0)));
    }

    @Test
    @DisplayName("블로그 검색 컨트롤러 실패 테스트")
    void searchBlogControllerFailTest() throws Exception {
        // given

        // when
        ResultActions response = mockMvc.perform(get("/v1.0/search/blog")
                .param("query", "")
                .param("sort", "ACCURACY")
                .param("page", "1")
                .param("size", "5"));

        // then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.code", is(ResponseCode.INVALID_PARAM.getCode())));
        response.andExpect(jsonPath("$.message", is(ResponseCode.INVALID_PARAM.getMessage())));
    }

    @Test
    @DisplayName("인기 검색어 조회 컨트롤러 성공 테스트")
    void getPopularKeywordsSuccessTest() throws Exception {
        // given

        // when
        ResultActions response = mockMvc.perform(get("/v1.0/search/popular/keyword"));

        // then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.code", is(ResponseCode.RESPONSE_OK.getCode())));
        response.andExpect(jsonPath("$.message", is(ResponseCode.RESPONSE_OK.getMessage())));
    }
}