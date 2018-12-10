# 1
SELECT 
    COUNT(*) AS SUM
FROM
    (SELECT 
        *
    FROM
        news UNION ALL SELECT 
        *
    FROM
        reviews) AS articles;
        
# 2
SELECT 
    nc_name, COUNT(n_id)
FROM
    task_site.news_categories
        LEFT JOIN
    news ON news.n_category = news_categories.nc_id
GROUP BY nc_name;

# 3
SELECT 
    rc_name, COUNT(r_id)
FROM
    task_site.reviews_categories
        LEFT JOIN
    reviews ON reviews.r_category = reviews_categories.rc_id
GROUP BY rc_id;

# 4
SELECT 
    category_name, MAX(date) AS last_date
FROM
    (SELECT 
        category_name, date
    FROM
        (SELECT 
        reviews_categories.rc_name AS category_name, r_dt AS date
    FROM
        reviews_categories
    JOIN reviews ON reviews_categories.rc_id = reviews.r_category) AS n_cat UNION ALL SELECT 
        category_name, date
    FROM
        (SELECT 
        news_categories.nc_name AS category_name, n_dt AS date
    FROM
        news_categories
    JOIN news ON news_categories.nc_id = news.n_category) AS r_cat) AS result
GROUP BY category_name;

# 5
SELECT 
    pages.p_name, banners.b_id, banners.b_url
FROM
    task_site.banners
        JOIN
    m2m_banners_pages ON banners.b_id = m2m_banners_pages.b_id
        JOIN
    pages ON pages.p_id = m2m_banners_pages.p_id
WHERE
    p_parent IS NULL;
    
# 6
SELECT 
    p_name
FROM
    (SELECT 
        m2m_banners_pages.p_id, pages.p_name
    FROM
        pages
    JOIN m2m_banners_pages ON pages.p_id = m2m_banners_pages.p_id
    GROUP BY p_id) AS result;
# 7
SELECT 
    p_name
FROM
    pages
        LEFT OUTER JOIN
    m2m_banners_pages ON pages.p_id = m2m_banners_pages.p_id
WHERE
    b_id IS NULL;
# 8
SELECT 
    banners.b_id, b_url, p_id
FROM
    task_site.m2m_banners_pages
        INNER JOIN
    banners ON m2m_banners_pages.b_id = banners.b_id
GROUP BY b_id;

# 9
SELECT 
    b_id, b_url
FROM
    (SELECT 
        banners.b_id, banners.b_url
    FROM
        banners
    LEFT OUTER JOIN m2m_banners_pages ON banners.b_id = m2m_banners_pages.b_id
    WHERE
        p_id IS NULL) AS result;
        
# 10
SELECT 
    *
FROM
    (SELECT 
        b_id, b_url, (b_click / b_show * 100) AS rate
    FROM
        task_site.banners) AS result
WHERE
    rate >= 80;

# 11
SELECT 
    p_name
FROM
    (SELECT 
        *
    FROM
        pages
    LEFT OUTER JOIN m2m_banners_pages USING (p_id)
    LEFT OUTER JOIN banners USING (b_id)
    WHERE
        b_text IS NOT NULL
    GROUP BY p_id) AS result;

# 12
SELECT 
    p_name
FROM
    (SELECT 
        *
    FROM
        pages
    LEFT OUTER JOIN m2m_banners_pages USING (p_id)
    LEFT OUTER JOIN banners USING (b_id)
    WHERE
        b_pic IS NOT NULL
    GROUP BY p_id) AS result;

# 13
SELECT 
    *
FROM
    (SELECT 
        task_site.news.n_header AS header, news.n_dt AS date
    FROM
        news UNION SELECT 
        reviews.r_header AS header, reviews.r_dt AS date
    FROM
        reviews) AS t
WHERE
    DATE(date) BETWEEN '2011-01-01' AND '2012-01-01';
    
# 14
SELECT 
    nc_id AS id, nc_name AS name
FROM
    task_site.news_categories
        LEFT OUTER JOIN
    news ON news_categories.nc_id = news.n_category
WHERE
    n_header IS NULL 
UNION SELECT 
    rc_id AS id, rc_name AS name
FROM
    task_site.reviews_categories
        LEFT OUTER JOIN
    reviews ON reviews_categories.rc_id = reviews.r_category
WHERE
    r_header IS NULL;
    
# 15
SELECT 
    news.n_header, news.n_dt
FROM
    news
        JOIN
    news_categories ON news.n_category = news_categories.nc_id
WHERE
    news_categories.nc_name = 'Логистика'
        AND DATE(n_dt) BETWEEN '2012-01-01' AND '2013-01-01';
        
# 16
SELECT 
    YEAR(n_dt) AS year, COUNT(*)
FROM
    task_site.news
GROUP BY YEAR(n_dt);

# 17
SELECT 
    banners.b_url, banners.b_id
FROM
    banners
        JOIN
    (SELECT 
        b_url
    FROM
        task_site.banners
    GROUP BY b_url
    HAVING COUNT(*) > 1) AS t ON banners.b_url = t.b_url;
    
# 18
SELECT 
    p_name, b_id, b_url
FROM
    (SELECT 
        p2.p_id, p2.p_name
    FROM
        (SELECT 
        *
    FROM
        task_site.pages
    WHERE
        p_name = 'Юридическим лицам') AS p1
    INNER JOIN pages AS p2 ON p1.p_id = p2.p_parent) AS t1
        JOIN
    (SELECT 
        banners.b_id, b_url, p_id
    FROM
        banners
    LEFT OUTER JOIN m2m_banners_pages ON banners.b_id = m2m_banners_pages.b_id) AS t2 ON t1.p_id = t2.p_id;
    
# 19 
SELECT 
    b_id, b_url, (b_click / b_show) AS rate
FROM
    task_site.banners
WHERE
    b_pic IS NOT NULL
ORDER BY rate DESC;

# 20
SELECT 
    header, date
FROM
    (SELECT 
        news.n_header AS header, news.n_dt AS date
    FROM
        news UNION SELECT 
        reviews.r_header AS header, reviews.r_dt AS date
    FROM
        reviews) AS t
ORDER BY date
LIMIT 1;

# 21
SELECT 
    b_url, b_id
FROM
    task_site.banners
GROUP BY b_url
HAVING COUNT(b_url) = 1
ORDER BY b_id;

# 22
SELECT 
    pages.p_name, t.banners_count
FROM
    (SELECT 
        p_id, COUNT(b_id) AS banners_count
    FROM
        task_site.m2m_banners_pages
    GROUP BY p_id) AS t
        JOIN
    pages
WHERE
    pages.p_id = t.p_id
ORDER BY banners_count DESC;

# 23 
SELECT 
    *
FROM
    (SELECT 
        news.n_header AS header, news.n_dt AS date
    FROM
        news
    ORDER BY date DESC
    LIMIT 1) AS t1 
UNION SELECT 
    *
FROM
    (SELECT 
        reviews.r_header AS header, reviews.r_dt AS date
    FROM
        reviews
    ORDER BY date DESC
    LIMIT 1) AS t2;
    
# 24
SELECT 
    b_id, b_url, b_text
FROM
    banners
WHERE
    b_text LIKE CONCAT('%', SUBSTRING(b_url, 8), '%');
    
# 25 --?
SELECT 
    pages.p_name
FROM
    pages
        JOIN
    (SELECT 
        t.b_id, p_id
    FROM
        (SELECT 
        b_id, b_click / b_show AS rate
    FROM
        task_site.banners
    ORDER BY rate DESC
    LIMIT 1) AS t
    JOIN m2m_banners_pages ON m2m_banners_pages.b_id = t.b_id) AS t2 ON pages.p_id = t2.p_id;
    
# 26
SELECT 
    AVG(b_click / b_show)
FROM
    task_site.banners
WHERE
    b_show > 0;
    
# 27
SELECT 
    AVG(b_click / b_show)
FROM
    task_site.banners
WHERE
    b_pic IS NULL;
    
# 28
SELECT 
    COUNT(*) AS COUNT
FROM
    task_site.m2m_banners_pages
        JOIN
    (SELECT 
        p_id
    FROM
        task_site.pages
    WHERE
        p_parent IS NULL) AS t
WHERE
    m2m_banners_pages.p_id = t.p_id;
    
# 29
SELECT 
    banners.b_id, b_url, count
FROM
    (SELECT 
        b_id, COUNT(p_id) AS count
    FROM
        task_site.m2m_banners_pages
    GROUP BY b_id
    ORDER BY count DESC
    LIMIT 1) AS t
        JOIN
    banners ON t.b_id = banners.b_id;
    
# 30
SELECT 
    p_name, COUNT(*) AS count
FROM
    m2m_banners_pages
        LEFT JOIN
    pages ON pages.p_id = m2m_banners_pages.p_id
GROUP BY p_name
HAVING COUNT(*) = (SELECT 
        COUNT(*)
    FROM
        m2m_banners_pages
    GROUP BY p_id ASC
    LIMIT 1);
