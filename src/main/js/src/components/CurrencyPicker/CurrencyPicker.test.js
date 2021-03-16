import { render, fireEvent } from '@testing-library/react';
import CurrencyPicker from '.';

test('test onchange', () => {
  const handleChange = jest.fn();
  const values = {
    from: '',
    to: '',
  };
  const { getByTestId, rerender } = render(
    <CurrencyPicker onChange={handleChange} value={values} />
  );
  fireEvent.change(getByTestId('from'), {
    target: { name: 'from', value: 'GBP' },
  });
  expect(handleChange).toHaveBeenCalledWith({ from: 'GBP', to: '' });

  rerender(
    <CurrencyPicker onChange={handleChange} value={{ from: 'GBP', to: '' }} />
  );

  fireEvent.change(getByTestId('from'), {
    target: { name: 'to', value: 'SEK' },
  });
  expect(handleChange).toHaveBeenCalledWith({ from: 'GBP', to: 'SEK' });
});
