const createSpaces = (array) => {
    return array.map((word) => word.replace(/_/g, " "));
  };
  
  export default createSpaces;