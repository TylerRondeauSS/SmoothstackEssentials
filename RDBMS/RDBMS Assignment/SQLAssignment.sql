#How many copies of the book titled The Lost Tribe are owned by the library branch whose name is "Sharpstown"?
SELECT bkc.noOfCopies
FROM tbl_book_copies bkc
INNER JOIN tbl_library_branch br ON br.branchId = bkc.branchId
INNER JOIN tbl_book bk on bk.bookId = bkc.bookId
WHERE title = 'The Lost Tribe' AND branchName = 'Sharpstown';

#How many copies of the book titled The Lost Tribe are owned by each library branch?
SELECT bkc.noOfCopies, br.branchName
FROM tbl_book_copies bkc
INNER JOIN tbl_library_branch br ON br.branchId = bkc.branchId
INNER JOIN tbl_book bk on bk.bookId = bkc.bookId
WHERE title='The Lost Tribe';

#Retrieve the names of all borrowers who do not have any books checked out.
SELECT bor.name
FROM tbl_borrower bor
LEFT JOIN tbl_book_loans bkl ON bor.cardNo = bkl.cardNo
WHERE bkl.cardNo IS NULL;

#For each book that is loaned out from the "Sharpstown" branch and whose DueDate is today, retrieve the book title, the borrower's name, and the borrower's address.
SELECT bk.title, bor.name, bor.address
FROM tbl_book bk
INNER JOIN tbl_book_loans bkl ON bkl.bookId = bk.bookId
INNER JOIN tbl_borrower bor ON bor.cardNo = bkl.cardNo
INNER JOIN tbl_library_branch br ON br.branchId = bkl.branchId
WHERE dueDate = CURDATE() AND branchName = 'Sharpstown';

#For each library branch, retrieve the branch name and the total number of books loaned out from that branch.
SELECT br.branchName, count(*) as numberOfLoans
FROM tbl_book_loans bkl
INNER JOIN tbl_library_branch br ON br.branchId = bkl.branchId
GROUP BY branchName;

#Retrieve the names, addresses, and number of books checked out for all borrowers who have more than five books checked out. 
SELECT bor.name, bor.address, count(*) as loans
FROM tbl_borrower bor
INNER JOIN tbl_book_loans bkl ON bor.cardNo = bkl.cardNo
GROUP BY name,address
HAVING count(*) >= 5;

#For each book authored (or co-authored) by "Stephen King", retrieve the title and the number of copies owned by the library branch whose name is "Central"
SELECT bk.title, bkc.noOfCopies
FROM tbl_book bk
INNER JOIN tbl_book_copies bkc ON bkc.bookId = bk.bookId
INNER JOIN tbl_library_branch br ON br.branchId = bkc.branchId
INNER JOIN tbl_author auth ON auth.authorId = bk.authId
WHERE authorName = "Stephen King" AND branchName = "Central";