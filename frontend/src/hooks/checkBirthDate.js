function checkBirthDate(birthDate) {
  let today = new Date();
  let birth = new Date(birthDate);

  let age = today.getFullYear() - birth.getFullYear();
  let monthDifference = today.getMonth() - birth.getMonth();
  let dayDifference = today.getDate() - birth.getDate();

  if (monthDifference < 0 || (monthDifference === 0 && dayDifference < 0)) {
    age--;
  }
  if (age >= 18) {
    //console.log("true");
    return true;
  } else {
    //console.log("false");
    return false;
  }
}

export default checkBirthDate;
