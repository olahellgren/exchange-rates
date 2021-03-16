import { Container, Grid, Typography } from '@material-ui/core';
import { useEffect, useState } from 'react';
import Rates from '../Rates';
import { useDebounce } from '../../utlis/useDebounce';
import {
  ExchangeRateProvider,
  useExchangeRates,
} from '../../context/exchangeRateContext';

const Home = () => {
  const { exchangeRateState, actions } = useExchangeRates();
  const [ratesValue, setRatesValue] = useState({ from: 'EUR', to: 'SEK' });
  const debouncedFromValue = useDebounce(ratesValue.from);
  const debouncedToValue = useDebounce(ratesValue.to);

  useEffect(() => {
    actions.fetchRates(debouncedFromValue, debouncedToValue);
  }, [debouncedFromValue, debouncedToValue, actions]);

  const handleOnChange = (values) => {
    setRatesValue(values);
  };

  return (
    <Container maxWidth="md">
      <Grid container alignItems="center" justify="center">
        <Typography align="center" variant="h1">
          Exchange Rates
        </Typography>
        <Rates onChange={handleOnChange} value={ratesValue} />
        <Typography>result</Typography>
      </Grid>
    </Container>
  );
};

export default Home;
