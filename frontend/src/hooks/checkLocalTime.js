const checkLocalTime = () => {
  const date = new Date();
  const hours = date.getHours();

  if (hours < 13) {
    return "Buenos Dias";
  } else if (hours > 19) {
    return "Buenas Noches";
  } else {
    return "Buenas Tardes";
  }
};
export default checkLocalTime;
