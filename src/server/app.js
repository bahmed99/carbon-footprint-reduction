const express = require("express");

const app = express()

const morgan = require('morgan')
app.use(morgan('tiny'))

app.use(express.json())

const cors = require('cors')
app.use(cors())

require('dotenv').config();
const port = process.env.PORT;

app.use('/', require('./routes/test'))


app.listen(port, () => {
    console.log(`runinig on port ${port}`)
})