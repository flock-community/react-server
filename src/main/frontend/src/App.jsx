import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import AlbumCard from './components/AlbumCard';
import data from './data/data';

const styles = theme => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    height: 140,
    width: 100,
  },
  control: {
    padding: theme.spacing.unit * 2,
  },
});

const App = ({ classes } = props) => (
  <Grid container justify="center" className={classes.root} spacing={16}>
    {data.map(
      albumData => <Grid item xs={12} key={albumData.idx}>
        <AlbumCard data={albumData}></AlbumCard>
      </Grid>
    )}
  </Grid>
);

export default withStyles(styles)(App);
