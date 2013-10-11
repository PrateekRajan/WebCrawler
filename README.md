WebCrawler
==========

A simple web crawler that crawl the web jumping from one site to another. To prevent the infinite looping it keeps track
of all the visited sites and only traverses those which if hasn't seen earlier. The distinction is based on the exact page
visited meaning www.google.com/images is different from www.google.com/finance
