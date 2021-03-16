import { ExchangeRateProvider } from '../../context/exchangeRateContext';
import Home from '../Home';

const App = () => {
  return (
    <ExchangeRateProvider>
      <Home />
    </ExchangeRateProvider>
  );
};

export default App;
