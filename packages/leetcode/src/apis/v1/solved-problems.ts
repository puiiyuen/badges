import { Enumeration, OpenAPIRoute, Str } from 'chanfana'
import { z } from 'zod'

import { difficulties } from '../../adapters/leetcode'
import { solvedProblemsBadge } from '../../services/solved-problems'

export class SolvedProblems extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode v1'],
    request: {
      query: z.object({
        difficulty: Enumeration({
          values: difficulties,
          default: 'All',
        }),
      }),
      params: z.object({
        username: Str({
          required: true,
        }),
      }),
    },
    responses: {
      200: {
        description: 'Successful Response',
        content: {
          'img/svg+xml': {
            schema: Str(),
          },
        },
      },
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { difficulty } = data.query
    const shield = await solvedProblemsBadge(username, difficulty)
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
