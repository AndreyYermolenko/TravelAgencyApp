function reservation(id) {
    if (confirm('Подтвердите бронирование тура')) {
        searchInfo(id);
    }

}

function searchInfo(id) {
    let request = new XMLHttpRequest();
    let url = "/api/reservationTour?id=" + id;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                if(request.responseText === "true") {
                    alert("Тур успешно забронирован!")
                } else {
                    alert("При бронировании тура произошла ошибка." +
                        "Возможно вы уже забронировали этот тур.")
                }
            }
        };

        request.open("GET", url);
        request.send();

    } catch (e) {
        alert("Unable connect to server");
    }
}