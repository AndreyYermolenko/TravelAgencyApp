function resultForUser(content) {
    let text;
    text = '<div class="scroll">\n';

    for (let i in content) {
        text += '        <table border="1" cellpadding="2" width="100%">\n' +
            '                <tr>\n' +
            '                    <th bgcolor="#dc143c">' + content[i].id + '</th>\n' +
            '                    <th><a href="/reservationTour?id=' + content[i].id + '">Забронировать тур</a></th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Название тура</th>\n' +
            '                    <th>' + content[i].destination + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Цена</th>\n' +
            '                    <th>' + content[i].cost + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Дата начала</th>\n' +
            '                    <th>' + content[i].beginDate.year + '-'
                                       + content[i].beginDate.monthValue + '-'
                                       + content[i].beginDate.dayOfMonth + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Дата окончания</th>\n' +
            '                    <th>' + content[i].beginDate.year + '-'
                                        + content[i].beginDate.monthValue + '-'
                                        + content[i].beginDate.dayOfMonth + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Описание</th>\n' +
            '                    <th>' + content[i].description + '</th>\n' +
            '                </tr>\n' +
            '            </table>\n' +
            '            <br>\n';
    }
    text += '</div>';
    return text;
}

function resultForManager(content) {
    let text;
    text = '    <input type="button" class="button" value="Добавить тур" onclick="location.href=\'addTour\'" />\n' +
           '    <br>'
    text += '<div class="scroll">\n';

    for (let i in content) {
        text += '        <table border="1" cellpadding="2" width="100%">\n' +
            '                <tr>\n' +
            '                    <th bgcolor="#dc143c">' + content[i].id + '</th>\n' +
            '                    <th>\n' +
            '                        <a href="/updateTour?id=' + content[i].id + '">Изменить тур</a>\n' +
            '                        <br>\n' +
            '                        <a onclick="return confirm(\'Подтвердите удаление тура\')"\n' +
            '                           href="/deleteTour?id=' + content[i].id + '">Удалить тур</a>\n' +
            '                    </th>\n' +
            '                </tr>' +
            '                <tr>\n' +
            '                    <th>Название тура</th>\n' +
            '                    <th>' + content[i].destination + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Цена</th>\n' +
            '                    <th>' + content[i].cost + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Дата начала</th>\n' +
            '                    <th>' + content[i].beginDate.year + '-'
                                        + content[i].beginDate.monthValue + '-'
                                        + content[i].beginDate.dayOfMonth + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Дата окончания</th>\n' +
            '                    <th>' + content[i].beginDate.year + '-'
                                        + content[i].beginDate.monthValue + '-'
                                        + content[i].beginDate.dayOfMonth + '</th>\n' +
            '                </tr>\n' +
            '                <tr>\n' +
            '                    <th>Описание</th>\n' +
            '                    <th>' + content[i].description + '</th>\n' +
            '                </tr>\n' +
            '            </table>\n' +
            '            <br>\n';
    }
    text += '</div>';
    return text;
}
