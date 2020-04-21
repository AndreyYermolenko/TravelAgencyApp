        function saveData() {
            let destination_value, beginDate_value, endDate_value, minCost_value, maxCost_value;
            destination_value = document.getElementById("destination").value;
            beginDate_value = document.getElementById("beginDate").value;
            endDate_value = document.getElementById("endDate").value;
            minCost_value = document.getElementById("minCost").value;
            maxCost_value = document.getElementById("maxCost").value;

            sessionStorage.setItem("destination", destination_value);
            sessionStorage.setItem("beginDate", beginDate_value);
            sessionStorage.setItem("endDate", endDate_value);
            sessionStorage.setItem("minCost", minCost_value);
            sessionStorage.setItem("maxCost", maxCost_value);

        }

    function loadData() {
        document.getElementById("destination").value = sessionStorage.getItem("destination");
        document.getElementById("beginDate").value = sessionStorage.getItem("beginDate");
        document.getElementById("endDate").value = sessionStorage.getItem("endDate");
        document.getElementById("minCost").value = sessionStorage.getItem("minCost");
        document.getElementById("maxCost").value = sessionStorage.getItem("maxCost");

    }
