import { Container, Grid, Typography } from '@material-ui/core';
import { useEffect, useState } from 'react';
import CurrencyPicker from '../CurrencyPicker';
import { useDebounce } from '../../utlis/useDebounce';
import { useExchangeRates } from '../../context/exchangeRateContext';

const Home = () => {
  const {
    exchangeRateState: { rates },
    actions,
  } = useExchangeRates();
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
        <CurrencyPicker onChange={handleOnChange} value={ratesValue} />
        <Typography variant="h2">
          {`${Number(rates.base.value).toFixed(2)} ${
            rates.base.currency
          } is ${Number(rates.to.value).toFixed(2)} ${rates.to.currency}`}
        </Typography>
      </Grid>
    </Container>
  );
};

export default Home;
