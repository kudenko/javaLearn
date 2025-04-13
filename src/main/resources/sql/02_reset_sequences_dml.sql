SELECT setval('author_id_seq', (SELECT MAX(id) FROM author));

SELECT setval('book_id_seq', (SELECT MAX(id) FROM book));

SELECT setval('journal_id_seq', (SELECT MAX(id) FROM journal));
