    const itemsPerPage = 10000; // Set number of items per page
    let currentPage = 1;
    let transactions = [];

    // Load CSV data
    d3.csv('chuyen_khoan.csv').then(data => {
        transactions = data;
        displayPage(currentPage);
        setupPagination();
    }).catch(error => {
        console.error('Error:', error);
    });

    function displayPage(page) {
        const tableBody = document.getElementById('transactionTableBody');
        tableBody.innerHTML = ''; // Clear previous entries

        const start = (page - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const pageItems = transactions.slice(start, end);

        pageItems.forEach(transaction => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${transaction.date_time}</td>
                <td>${transaction.trans_no}</td>
                <td>${transaction.credit}</td>
                <td>${transaction.debit}</td>
                <td>${transaction.detail}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function setupPagination() {
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = ''; // Clear previous pagination

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