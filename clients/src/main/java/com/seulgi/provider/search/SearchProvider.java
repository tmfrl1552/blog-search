package com.seulgi.provider.search;

import com.seulgi.dto.search.SearchBlogReq;
import com.seulgi.dto.search.SearchBlogRes;

public interface SearchProvider {

    SearchBlogRes searchBlog(SearchBlogReq req);

}
