import React, { useCallback } from 'react';
import PropTypes from 'prop-types';
import { Grid, TextField } from '@material-ui/core';

const CurrencyPicker = ({ onChange, value }) => {
  const handleChange = useCallback(
    ({ target: { value: inputValue, name } }) => {
      onChange({
        from: name === 'from' ? inputValue.toUpperCase() : value.from,
        to: name === 'to' ? inputValue.toUpperCase() : value.to,
      });
    },
    [onChange, value]
  );

  return (
    <Grid container justify="center" alignItems="center" spacing={4}>
      <Grid item>
        <TextField
          inputProps={{ 'data-testid': 'from' }}
          label="From"
          name="from"
          variant="outlined"
          onChange={handleChange}
          value={value.from}
        />
      </Grid>
      <Grid item>
        <TextField
          inputProps={{ 'data-testid': 'to' }}
          label="To"
          name="to"
          variant="outlined"
          onChange={handleChange}
          value={value.to}
        />
      </Grid>
    </Grid>
  );
};

CurrencyPicker.propTypes = {
  value: PropTypes.shape({ from: PropTypes.string, to: PropTypes.string }),
};

export default CurrencyPicker;
