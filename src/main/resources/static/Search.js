`` const searchInputs = document.querySelectorAll('input');
    const tableBody = document.getElementById('transactionTableBody');

    searchInputs.forEach(input => {
        input.addEventListener('keyup', function () {
            const searchValues = Array.from(searchInputs).map(input => input.value.toLowerCase());
            const rows = tableBody.getElementsByTagName('tr');

            Array.from(rows).forEach(row => {
                const cells = row.getElementsByTagName('td');
                let match = true;

                for (let i = 0; i < cells.length; i++) {
                    const cell = cells[i];
                    if (cell) {
                        const cellText = cell.textContent.toLowerCase();
                        if (searchValues[i] && !cellText.includes(searchValues[i])) {
                            match = false;
                            break;
                        }
                    }
                }

                row.style.display = match ? '' : 'none';
            });
        });
    });``