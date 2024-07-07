import { fromHono } from 'chanfana'
import { Hono } from 'hono'

import { AcceptedRate } from './apis/accepted-rates'
import { Username } from './apis/username'
import { Ranking } from './apis/ranking'
import { SolvedProblems } from './apis/solved-problems'
import { Submissions } from './apis/submissions'

const app = new Hono()

const openapi = fromHono(app, {
  docs_url: '/',
})

openapi.get('/leetcode/:username', Username)
openapi.get('/leetcode/:username/ranking', Ranking)
openapi.get('/leetcode/:username/rate', AcceptedRate)
openapi.get('/leetcode/:username/solved', SolvedProblems)
openapi.get('/leetcode/:username/submissions', Submissions)

export default app
