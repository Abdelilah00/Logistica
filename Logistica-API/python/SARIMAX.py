import itertools
import pandas as pd
import statsmodels.api as sm
import warnings
from flask import Flask
from flask import request
from statsmodels.tsa.stattools import adfuller

warnings.filterwarnings('ignore')
app = Flask(__name__)


def adf_test(timeseries):
    # Perform Dickey-Fuller test:
    print('Results of Dickey-Fuller Test:')
    dftest = adfuller(timeseries, autolag='AIC')
    dfoutput = pd.Series(dftest[0:4], index=['Test Statistic', 'p-time', '#Lags Used', 'Number of Observations Used'])
    for key, time in dftest[4].items():
        dfoutput['Critical time (%s)' % key] = time
    print(dfoutput)


def best_params(timeseries):
    p = range(0, 3)
    d = range(1, 2)
    q = range(0, 3)
    pdq = list(itertools.product(p, d, q))
    seasonal_pdq = [(x[0], x[1], x[2], 12) for x in list(itertools.product(p, d, q))]

    ans = []
    for param in pdq:
        for param_seasonal in seasonal_pdq:
            try:
                mod = sm.tsa.statespace.SARIMAX(timeseries,
                                                order=param,
                                                seasonal_order=param_seasonal,
                                                enforce_stationarity=False,
                                                enforce_invertibility=False)
                results = mod.fit()
                ans.append([param, param_seasonal, results.bic])
            except:
                continue

    # Convert into dataframe
    ans_df = pd.DataFrame(ans, columns=['pdq', 'pdqs', 'bic'])
    # Sort and return top 5 combinations
    return ans_df.sort_values(by=['bic'], ascending=True)[0:5]


@app.route('/predict', methods={'POST'})
def predict():
    series = request.form.get('series')
    forecast = int(request.form.get('forecast'))
    ts_data = pd.read_json(series)
    # converting 'time' column to type 'datetime' so that indexing can happen later
    ts_data['time'] = pd.to_datetime(ts_data['time'])

    ts_data.isnull().sum()
    ts_data = ts_data.dropna()
    ts_data.isnull().sum()

    ts_data = ts_data.set_index('time')

    # todo make function to calculate
    mod = sm.tsa.statespace.SARIMAX(ts_data,
                                    order=(1, 1, 2),
                                    seasonal_order=(2, 1, 2, 12),
                                    enforce_stationarity=False,
                                    enforce_invertibility=False)
    results = mod.fit()
    df1 = pd.DataFrame(results.predict())
    df2 = pd.DataFrame(results.forecast(forecast))
    df = pd.concat([df1, df2])
    response = df.to_json(date_format='iso', orient='table')

    # return more details
    return response, 200


app.run(host='localhost', port=5000)
