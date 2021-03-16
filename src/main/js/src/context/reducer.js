import { SET_RATES } from './actions';

export const initialExchangeRateState = {
  rates: {
    date: null,
    base: {
      currency: null,
      value: null,
    },
    to: {
      currency: null,
      value: null,
    },
  },
};

export const reducer = (state, action) => {
  switch (action.type) {
    case SET_RATES:
      const rateState = { ...state, rates: action.rates };
      return rateState;
    default:
      return state;
  }
};
