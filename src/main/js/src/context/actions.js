export const SET_RATES = 'SET_RATES';

export const createActions = (dispatch) => {
  return {
    fetchRates: async (from, to) => {
      try {
        console.log(`${from} -> ${to}`);
        const result = await fetch(`api/exchange-rates/${to}/${from}`);
        const rates = await result.json();
        dispatch({ type: SET_RATES, rates });
      } catch (error) {
        console.error(error);
      }
    },
  };
};
