package com.seulgi.provider.search;

import com.seulgi.dto.search.SearchBlogsReq;

public interface SearchProvider {

    public void searchBlog(SearchBlogsReq req);

}
