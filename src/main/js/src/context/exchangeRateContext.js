import React, {
  createContext,
  useContext,
  useEffect,
  useMemo,
  useReducer,
  useState,
} from 'react';
import { createActions } from './actions';
import { initialExchangeRateState, reducer } from './reducer';

const ExchangeRateContext = createContext();

export const ExchangeRateProvider = ({ children }) => {
  const [exchangeRateState, dispatch] = useReducer(
    reducer,
    initialExchangeRateState
  );

  const [actions, setActions] = useState(createActions(dispatch));

  useEffect(() => {
    setActions(createActions(dispatch));
  }, [dispatch]);

  const value = useMemo(() => ({ exchangeRateState, actions }), [
    exchangeRateState,
    actions,
  ]);

  return (
    <ExchangeRateContext.Provider value={value}>
      {children}
    </ExchangeRateContext.Provider>
  );
};

export const useExchangeRates = () => useContext(ExchangeRateContext);
