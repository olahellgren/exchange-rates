import { render } from '@testing-library/react';
import App from '.';

test('renders learn react link', () => {
  const { getByText } = render(<App />);
  expect(getByText(/Exchange Rates/i)).toBeTruthy();
});
