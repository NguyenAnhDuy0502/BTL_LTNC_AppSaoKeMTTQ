    const itemsPerPage = 10000; // Set number of items per page
    let currentPage = 1;
    let transactions = [];

    getAll();

    function getAll() {
    fetch('http://localhost:8080/api/all', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(response => {
                   return response.json();
            }).then(data => {
                transactions = data;
                displayPage(currentPage);
                setupPagination();
            }).catch(error => {
                console.error('Error:', error);
            });
    }

    function deleteTransaction() {
        let transactionNo = document.getElementById('DeleteId').value;

        // Tạo URL với các query parameters
            let url = new URL('http://localhost:8080/api/delete');
            url.searchParams.append('TransactionNo', transactionNo);

           fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
            }).then(response => {
                return response.json();
            }).then(data => {
                transactions = data;
                displayPage(currentPage);
                setupPagination();
            }).catch(error => {
                console.error('Error:', error);
            });
    }

    function searchTransaction() {
        //Kiểm tra performance
        const startTime = performance.now();

        let date = document.getElementById('searchDate').value;
        let transactionNo = document.getElementById('searchTransNo').value;
        let credit = document.getElementById('searchCredit').value;
        let debit = document.getElementById('searchDebit').value;
        let detail = document.getElementById('searchDetail').value;

        if (!date && !transactionNo && !credit && !debit && !detail)
           getAll();
        else {
        // Tạo URL với các query parameters
            let url = new URL('http://localhost:8080/api/search');
            url.searchParams.append('Date', date);
            url.searchParams.append('TransactionNo', transactionNo);
            url.searchParams.append('Credit', credit);
            url.searchParams.append('Debit', debit);
            url.searchParams.append('Detail', detail);

           fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
            }).then(response => {
                return response.json();
            }).then(data => {
                transactions = data;
                displayPage(currentPage);
                setupPagination();
            }).catch(error => {
                console.error('Error:', error);
            });
        }
        //Kiểm tra performance, do độ chính xác chỉ tới 1/1000 ms nên cần làm tròn
        const endTime = performance.now();
        const duration = endTime - startTime;
        console.log(`Thời gian thực hiện: ${duration.toFixed(3)} ms`);
    }

    function displayPage(page) {
        const tableBody = document.getElementById('transactionTableBody');
        tableBody.innerHTML = '';

        const start = (page - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const pageItems = transactions.slice(start, end);

        pageItems.forEach(transaction => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${transaction.dateTime}</td>
                <td>${transaction.transNo}</td>
                <td>${transaction.credit}</td>
                <td>${transaction.debit}</td>
                <td>${transaction.detail}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function setupPagination() {
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = '';

        const pageCount = Math.ceil(transactions.length / itemsPerPage);

        for (let i = 1; i <= pageCount; i++) {
            const li = document.createElement('li');
            li.className = 'page-item';
            li.innerHTML = `<a class="page-link" href="#">${i}</a>`;
            li.addEventListener('click', function (e) {
                e.preventDefault();
                currentPage = i;
                displayPage(currentPage);
            });
            pagination.appendChild(li);
        }
    }