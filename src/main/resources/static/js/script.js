let apiStructure = {
    type: 'line',
    data: {
        labels: [],
        datasets: []
    },
    options: {
        title: {
            display: false,
            text: 'World population per region (in millions)'
        }
    }
}

let datasets = {
    tested: {
        data: [],
        label: "Testovaných",
        borderColor: "#42ff00",
        fill: false
    },
    infected: {
        data: [],
        label: "Nakažených",
        borderColor: "#ffd642",
        fill: false
    },
    recovered: {
        data: [],
        label: "Vyléčených",
        borderColor: "#42f9ff",
        fill: false
    },
    dead: {
        data: [],
        label: "Zemřelých",
        borderColor: "#ff4242",
        fill: false
    },
}

let dataMap = new Map([
    ['kumulovany_pocet_nakazenych', 'infected'],
    ['kumulovany_pocet_vylecenych', 'recovered'],
    ['kumulovany_pocet_umrti', 'dead'],
    ['kumulovany_pocet_provedenych_testu', 'tested'],
])

async function fetchData() {
    return new Promise(async (resolve) => {
        let data = await fetch('https://onemocneni-aktualne.mzcr.cz/api/v1/covid-19/nakazeni-vyleceni-umrti-testy.json')
        let response = await data.json()


        document.getElementById("day").innerText = new Intl.DateTimeFormat('cs', {
            day: 'numeric',
            month: 'numeric'
        }).format(new Date(response.data[response.data.length - 1].datum));
        document.getElementById("dayTested").innerText = response.data[response.data.length - 1].kumulovany_pocet_provedenych_testu - response.data[response.data.length - 2].kumulovany_pocet_provedenych_testu;
        document.getElementById("dayInfected").innerText = response.data[response.data.length - 1].kumulovany_pocet_nakazenych - response.data[response.data.length - 2].kumulovany_pocet_nakazenych;
        document.getElementById("dayCured").innerText = response.data[response.data.length - 1].kumulovany_pocet_vylecenych - response.data[response.data.length - 2].kumulovany_pocet_vylecenych;
        document.getElementById("dayDead").innerText = response.data[response.data.length - 1].kumulovany_pocet_umrti - response.data[response.data.length - 2].kumulovany_pocet_umrti;

        document.getElementById("tested").innerText = response.data[response.data.length - 1].kumulovany_pocet_provedenych_testu;
        document.getElementById("infected").innerText = response.data[response.data.length - 1].kumulovany_pocet_nakazenych;
        document.getElementById("cured").innerText = response.data[response.data.length - 1].kumulovany_pocet_vylecenych;
        document.getElementById("dead").innerText = response.data[response.data.length - 1].kumulovany_pocet_umrti;

        response = response.data.slice(Math.max(response.data.length - 30, 1))
        response.forEach(el => {
            let date = new Date(el.datum)
            const month = new Intl.DateTimeFormat('cs', {month: 'numeric'}).format(date);
            const day = new Intl.DateTimeFormat('cs', {day: 'numeric'}).format(date);

            apiStructure.data.labels.push(`${day}${month}.`)
            for (let [k, v] of Object.entries(el)) {
                if (dataMap.get(k)) {
                    datasets[dataMap.get(k)].data.push(v)
                }
            }
        });
        for (let [k, v] of Object.entries(datasets)) {
            apiStructure.data.datasets.push(v)
        }

        resolve(apiStructure)
    })
}

window.onload = async () => {
    let data = await fetchData()
    new Chart(document.getElementById("chart"), data)
}