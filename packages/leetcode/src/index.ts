import { fromHono } from 'chanfana'
import { Hono } from 'hono'

import {
  AcceptedRate as AcceptedRateV1,
  Ranking as RankingV1,
  SolvedProblems as SolvedProblemsV1,
  Submissions as SubmissionsV1,
  Username as UsernameV1,
} from './apis/v1'
import {
  AcceptedRate,
  Ranking,
  SolvedProblems,
  Submissions,
  Username,
} from './apis/v2'

const app = new Hono()

const openapi = fromHono(app, {
  docs_url: '/docs/leetcode',
})

// v2 Apis
openapi.get('/v2/leetcode/:username', Username)
openapi.get('/v2/leetcode/:username/ranking', Ranking)
openapi.get('/v2/leetcode/:username/rates', AcceptedRate)
openapi.get('/v2/leetcode/:username/solvedProblems', SolvedProblems)
openapi.get('/v2/leetcode/:username/submissions', Submissions)

// Legacy v1 apis
openapi.get('/leetcode/:username/name', UsernameV1)
openapi.get('/leetcode/:username/ranking', RankingV1)
openapi.get('/leetcode/:username/rate', AcceptedRateV1)
openapi.get('/leetcode/:username/solved', SolvedProblemsV1)
openapi.get('/leetcode/:username/submission', SubmissionsV1)

export default app
