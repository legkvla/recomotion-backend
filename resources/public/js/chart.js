$( document ).ready(function() {
  new Chart(document.getElementById("line-chart"), {
    type: 'line',
    data: {
      labels: [1,2,3,4,5,6,7,8,9,10],
      datasets: [{
          data: [86,114,106,106,107,111,133,221,783,2478],
          label: "Где смешно",
          borderColor: "#3e95cd",
          fill: false
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Где смешно'
      }
    }
  });
});
